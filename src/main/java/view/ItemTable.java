package view;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Category;
import entity.Image;
import entity.Item;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import logic.CategoryLogic;
import logic.ImageLogic;
import logic.ItemLogic;

/**
 *
 * @author Shariar (Shawn) Emami
 */
@WebServlet(name = "ItemTable", urlPatterns = {"/ItemTable"})
public class ItemTable extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Items Table List</title>");
            //https://www.w3schools.com/css/css_table.asp
            out.println("<style>");
            out.println("table {border-collapse: collapse; width: auto; margin-left: auto; margin-right: auto;}");
            out.println("th, td {text-align: left;padding: 8px;}");
            out.println("tr:nth-child(even) {background-color: #f2f2f2;}");
            out.println("td.edit{width:65px;}");
            out.println("td.delete{text-align: center;}");
            out.println("input.editor{width: 100%;}");
            out.println("input.update{width: 100%;}");
            out.println("</style>");
            out.println("<script>");
            out.println("var isEditActive = false;");
            out.println("var activeEditID = -1;");
            out.println("function createTextInput(text, name) {");
            out.println("var node = document.createElement(\"input\");");
            out.println("node.name = name;");
            out.println("node.className = \"editor\";");
            out.println("node.type = \"text\";");
            out.println("node.value = text;");
            out.println("return node;");
            out.println("}");
            out.println("function convertCellToInput( id, readOnly, name){");
            out.println("var idCell = document.getElementById(id);");
            out.println("var idInput = createTextInput(idCell.innerText, name);");
            out.println("idInput.readOnly = readOnly;");
            out.println("idCell.innerText = null;");
            out.println("idCell.appendChild(idInput);");
            out.println("}");
            out.println("window.onload = function () {");
            out.println("var elements = document.getElementsByClassName(\"edit\");");
            out.println("for (let i = 0; i < elements.length; i++) {");
            out.println("elements[i].childNodes[0].onclick = function () {");
            out.println("var id = elements[i].id;");
            out.println("if (isEditActive) {");
            out.println("if (activeEditID === id) {");
            out.println("this.type = \"submit\";");
            out.println("}");
            out.println("return;");
            out.println("}");
            out.println("isEditActive = true;");
            out.println("activeEditID = id;");
            out.println("this.value = \"Update\";");
            ItemLogic logic = new ItemLogic();
            logic.getColumnCodes().forEach((columnCode) -> {
                out.printf("convertCellToInput( ++id, false, %s);", columnCode);
            });
            out.println("};");
            out.println("}");
            out.println("};");
            out.println("</script>");
            out.println("</head>");
            out.println("<body>");
            //https://www.w3schools.com/css/css_table.asp
            out.println("<form method=\"post\">");
            out.println("<table>");

            out.println("<tr><th>Items Table List</th></tr>");
            out.println("</table>");  
            out.println("<table border=\"1\">");

            out.println("<tr>");
            logic.getColumnNames().forEach((columnName) -> {
                out.printf("<th>%s</th>", columnName);
            });
            out.println("</tr>");
            List<Item> entities = logic.getAll();
            long counter = 0;
            int recordsCounter=0;             
            for (Item entity : entities) {
             
                List<?> data = logic.extractDataAsList(entity);
                List<String> codes = logic.getColumnCodes();
                for(int i=0; i<data.size();i++){
                    out.printf("<td class=\"%s\" id=\"%d\" >%s</td>", codes.get(i), counter++, data.get(i));
                }
                out.println("</tr>");
                recordsCounter++;
            }
            out.println("<tr>");
            out.printf("<td>Total Records</td><td>%s</td>", recordsCounter);
            out.println("</tr>");
            out.println("</table>");
            out.println("</form>");
            out.printf("<div style=\"text-align: center;\"><pre>%s</pre></div>", toStringMap(request.getParameterMap()));
            out.println("</body>");
            out.println("</html>");
        }
    }

    private String toStringMap(Map<String, String[]> m) {
        StringBuilder builder = new StringBuilder();
        for (String k : m.keySet()) {
            builder.append("Key=").append(k)
                    .append(", ")
                    .append("Value/s=").append(Arrays.toString(m.get(k)))
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log("GET");
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log("POST");
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Sample of Account View Normal";
    }

    private static final boolean DEBUG = true;

    public void log(String msg) {
        if (DEBUG) {
            String message = String.format("[%s] %s", getClass().getSimpleName(), msg);
            getServletContext().log(message);
        }
    }

    public void log(String msg, Throwable t) {
        String message = String.format("[%s] %s", getClass().getSimpleName(), msg);
        getServletContext().log(message, t);
    }
}

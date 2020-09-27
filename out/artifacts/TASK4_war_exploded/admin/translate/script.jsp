<%@ page import="db.Language" %>
<script>
    function deleteModalFill(id) {
        $("#deleteId").val(id);
    }
    function editModalFill(id, name, obj) {
        $("#editId").val("");
        $("#nameEditInput").val("");
        <%  for (Language l : languages) {  %>
        $("#langEditInput<%=l.getId()%>").val("");
        $("#editId<%=l.getId()%>").val("");
        <%  }   %>

        $("#editId").val(id);
        $("#nameEditInput").val(name);
    <%  for (Language l : languages) {  %>
        $("#langEditInput<%=l.getId()%>").val(obj["<%=l.getId()%>"].word);
        $("#editId<%=l.getId()%>").val(obj["<%=l.getId()%>"].id);
    <%  }   %>
    }
</script>
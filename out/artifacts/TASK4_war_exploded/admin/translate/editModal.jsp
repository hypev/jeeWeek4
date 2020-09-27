<%@ page import="db.Language" %>
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Editing Translation</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="/admin/translation/edit" id="editForm">
                    <div class="form-group">
                        <label for="nameEditInput">Key:</label>
                        <input name="name" type="text" class="form-control" id="nameEditInput" placeholder="Key of translation..." required max="255">
                    </div>
                <%  for (Language l : languages) {  %>
                    <div class="form-group">
                        <label for="langEditInput<%=l.getId()%>"><%=l.getName()%>:</label>
                        <input name="word<%=l.getCode()%>" type="text" class="form-control" id="langEditInput<%=l.getId()%>" placeholder="Translation..." required max="255">
                        <input name="editId<%=l.getId()%>" type="hidden" id="editId<%=l.getId()%>">
                    </div>
                <%  }   %>
                    <input type="hidden" name="id" id="editId">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button class="btn btn-success" form="editForm">Save</button>
            </div>
        </div>
    </div>
</div>
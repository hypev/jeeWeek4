<%@ page import="db.Language" %>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addModalLabel">Add new translation key</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="/admin/translation/add" id="addForm">
                    <div class="form-group">
                        <label for="nameInput">Key:</label>
                        <input name="name" type="text" class="form-control" id="nameInput" placeholder="Key of translation..." required max="255">
                    </div>
                <%  for (Language l : languages) {  %>
                    <div class="form-group">
                        <label for="langInput"><%=l.getName()%>:</label>
                        <input name="word<%=l.getCode()%>" type="text" class="form-control" id="langInput" placeholder="Translation..." required max="255">
                    </div>
                <%  }   %>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button class="btn btn-success" form="addForm">+ Add</button>
            </div>
        </div>
    </div>
</div>
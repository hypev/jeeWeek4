<%@ page import="db.Language" %>
<%@ page import="db.Publication" %>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addModalLabel">Add News</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="/admin/news/add" id="addForm">
                    <div class="form-group">
                        <label for="titleInput">Title:</label>
                        <input name="title" type="text" class="form-control" id="titleInput" placeholder="Title of news..." required max="255">
                    </div>
                    <div class="form-group">
                        <label for="shortContentInput">Short Content:</label>
                        <textarea name="shortContent" class="form-control" id="shortContentInput" placeholder="Short content of news..." rows="3" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="contentInput">Content:</label>
                        <textarea name="content" class="form-control" id="contentInput" placeholder="Content of news..." rows="6" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="languageInput">Language:</label>
                        <select class="form-control" id="languageInput" name="languageId" required>
                        <%  for (Language l : languages) {  %>
                            <option value="<%=l.getId()%>"><%=l.getName()%></option>
                        <%  }   %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="publicationInput">Publication:</label>
                        <select class="form-control" id="publicationInput" name="publicationId" required>
                        <%  for (Publication p : publications) {    %>
                            <option value="<%=p.getId()%>"><%=p.getName()%></option>
                        <%  }   %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="pictureUrlInput">Picture URL:</label>
                        <input name="imgUrl" type="url" class="form-control" id="pictureUrlInput" placeholder="URL of Picture..." required max="255" required>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button class="btn btn-success" form="addForm">+ Add</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Editing News</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="/admin/news/edit" id="editForm">
                    <div class="form-group">
                        <label for="titleEditInput">Title:</label>
                        <input name="title" type="text" class="form-control" id="titleEditInput" placeholder="Title of news..." required max="255">
                    </div>
                    <div class="form-group">
                        <label for="shortContentEditInput">Short Content:</label>
                        <textarea name="shortContent" class="form-control" id="shortContentEditInput" placeholder="Short content of news..." rows="3" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="contentEditInput">Content:</label>
                        <textarea name="content" class="form-control" id="contentEditInput" placeholder="Content of news..." rows="6" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="languageIdEditInput">Language:</label>
                        <select class="form-control" id="languageIdEditInput" name="languageId" required>
                        <%  for (Language l : languages) {  %>
                            <option value="<%=l.getId()%>"><%=l.getName()%></option>
                        <%  }   %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="publicationIdEditInput">Publication:</label>
                        <select class="form-control" id="publicationIdEditInput" name="publicationId" required>
                        <%  for (Publication p : publications) {    %>
                            <option value="<%=p.getId()%>"><%=p.getName()%></option>
                        <%  }   %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="pictureUrlEditInput">Picture URL:</label>
                        <input name="imgUrl" type="url" class="form-control" id="pictureUrlEditInput" placeholder="URL of Picture..." required max="255" required>
                    </div>
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
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Editing Language</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="/admin/languages/edit" id="editLanguage">
                    <div class="form-group">
                        <label for="nameEditInput">Name:</label>
                        <input name="name" type="text" class="form-control" id="nameEditInput" placeholder="Name of language..." required max="255">
                    </div>
                    <div class="form-group">
                        <label for="codeEditInput">Code:</label>
                        <input name="code" type="text" class="form-control" id="codeEditInput" placeholder="Code of language..." required maxlength="3">
                    </div>
                    <input type="hidden" name="id" id="editId">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <button class="btn btn-success" form="editLanguage">Save</button>
            </div>
        </div>
    </div>
</div>
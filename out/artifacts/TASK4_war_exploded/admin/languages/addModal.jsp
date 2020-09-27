<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addModalLabel">Add new language</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="/admin/languages/add" id="addForm">
                    <div class="form-group">
                        <label for="nameInput">Name:</label>
                        <input name="name" type="text" class="form-control" id="nameInput" placeholder="Name of language..." required max="255">
                    </div>
                    <div class="form-group">
                        <label for="codeInput">Code:</label>
                        <input name="code" type="text" class="form-control" id="codeInput" placeholder="Code of language..." required maxlength="3">
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
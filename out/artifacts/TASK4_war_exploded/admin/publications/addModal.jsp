<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addModalLabel">Add new publication</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="/admin/publications/add" id="addForm">
                    <div class="form-group">
                        <label for="nameInput">Name:</label>
                        <input name="name" type="text" class="form-control" id="nameInput" placeholder="Name of publication..." required max="255">
                    </div>
                    <div class="form-group">
                        <label for="descriptionInput">Description:</label>
                        <textarea name="description" class="form-control" id="descriptionInput" placeholder="Description of publication..." rows="6" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="ratingInput">Rating:</label>
                        <input name="rating" type="number" class="form-control" id="ratingInput" placeholder="Rating of publication..." required step="0.1" min="0.0" max="10.0">
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
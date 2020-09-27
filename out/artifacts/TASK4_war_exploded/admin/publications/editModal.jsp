<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editModalLabel">Editing Publication</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form method="post" action="/admin/publications/edit" id="editForm">
                    <div class="form-group">
                        <label for="nameEditInput">Name:</label>
                        <input name="name" type="text" class="form-control" id="nameEditInput" placeholder="Name of publication..." required max="255">
                    </div>
                    <div class="form-group">
                        <label for="descriptionEditInput">Description:</label>
                        <textarea name="description" class="form-control" id="descriptionEditInput" placeholder="Description of publication..." rows="6" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="ratingEditInput">Rating:</label>
                        <input name="rating" type="number" class="form-control" id="ratingEditInput" placeholder="Rating of publication..." required step="0.1" min="0.0" max="10.0">
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
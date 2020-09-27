<script>
    function deleteModalFill(id) {
        $("#deleteId").val(id);
    }
    function editModalFill(id, rating, name, description) {
        $("#editId").val(id);
        $("#ratingEditInput").val(rating);
        $("#nameEditInput").val(name);
        $("#descriptionEditInput").val(description);
    }
</script>
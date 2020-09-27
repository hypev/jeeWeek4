<script>
    function deleteModalFill(id) {
        $("#deleteId").val(id);
    }
    function editModalFill(id, title, shortContent, content, imgUrl, languageId, publicationId) {
        $("#editId").val(id);
        $("#titleEditInput").val(title);
        $("#shortContentEditInput").val(shortContent);
        $("#contentEditInput").val(content);
        $("#pictureUrlEditInput").val(imgUrl);
        $("#languageIdEditInput").val(languageId);
        $("#publicationIdEditInput").val(publicationId);
    }
</script>
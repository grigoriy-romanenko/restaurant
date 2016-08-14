$(document).ready(function () {
    editMenuItemPopupSetup();
    setDeleteMenuItemEvent();
});

function editMenuItemPopupSetup() {
    var dialog = $("#editMenuItemPopup").dialog({
        autoOpen: false,
        resizable: false,
        modal: true
    });
    $(dialog).css("overflow", "hidden");
    $("#editMenuItemForm").submit(function (event) {
        event.preventDefault();
        $.ajax({
            type: "PUT",
            contentType: 'application/json',
            data: JSON.stringify({
                title: $("#titleInput").val(),
                price: $("#priceInput").val(),
                category: {
                    id: $("#categoryInput").val()
                }
            }),
            success: function () {
                dialog.dialog("close");
                window.location.reload(true);
            },
            error: function (xhr) {
                alert(xhr.responseText);
            }
        });
    });
    $("#editMenuItemButton").click(function () {
        dialog.dialog("open");
        $("#titleInput").val($("#title").text());
        $("#priceInput").val($("#price").text());
        $.ajax({
            url: "/restaurant/menu/categories",
            success: function (response) {
                var categories = $.map(response, function (category) {
                    return new Option(category.title, category.id);
                });
                $("#categoryInput").append(categories);
            },
            error: function () {
                alert("Error while getting menu categories");
            }
        });
    });
}

function setDeleteMenuItemEvent() {
    $("#deleteMenuItemButton").click(function () {
        if (confirm("Are you sure want to delete this menu item?")) {
            $.ajax({
                type: "DELETE",
                success: function () {
                    window.location.href = "/restaurant/menu";
                },
                error: function () {
                    alert("Error while deleting menu item");
                }
            });
        }
    });
}
$(document).ready(function () {
    editMenuItemPopupSetup();
    setDeleteMenuItemEvent();
});

function editMenuItemPopupSetup() {
    var dialog = $("#editMenuItemPopup").dialog({
        autoOpen: false
    });
    $("#editMenuItemForm").submit(function () {
        return false;
    });
    $("#submitMenuItem").click(function () {
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
            error: function () {
                alert("Error while updating menu item");
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
        $.ajax({
            type: "DELETE",
            error: function () {
                alert("Error while deleting menu item");
            }
        });
    });
}
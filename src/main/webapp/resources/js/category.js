$(document).ready(function () {
    createMenuItemFormSetup();
    setAddToCartEvent();
});

function createMenuItemFormSetup() {
    var dialog = $("#createMenuItemPopup").dialog({
        autoOpen: false,
        resizable: false,
        modal: true
    });
    $(dialog).css("overflow", "hidden");
    $("#createMenuItemForm").submit(function () {
        return false;
    });
    $("#submitMenuItem").click(function () {
        var url = window.location.pathname;
        var categoryId = url.substring(url.lastIndexOf('/') + 1);
        $.ajax({
            url: "/restaurant/menu/categories/" + categoryId + "/items",
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify({
                title: $("#title").val(),
                price: $("#price").val()
            }),
            success: function () {
                dialog.dialog("close");
                window.location.reload(true);
            },
            error: function () {
                alert("Error while creating menu item");
            }
        });
    });
    $("#createMenuItemButton").click(function () {
        dialog.dialog("open");
    });
}

function setAddToCartEvent() {
    $(".add-to-cart-btn").each(function () {
        var menuItemId = $(this).attr("data-menu-item");
        var username = $(this).attr("data-username");
        $(this).click(function () {
            $.ajax({
                url: "/restaurant/users/" + username + "/cart",
                type: "POST",
                data: {
                    menuItemId: menuItemId
                },
                error: function () {
                    alert("Error while adding menu item to cart");
                }
            });
        });
    });
}
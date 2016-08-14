$(document).ready(function () {
    setDeleteFromCartEvent();
    setPurchaseEvent();
});

function setDeleteFromCartEvent() {
    $(".delete-from-cart-btn").each(function () {
        $(this).click(function () {
            var menuItemId = $(this).attr("data-menu-item");
            var username = $(this).attr("data-username");
            $.ajax({
                url: "/restaurant/users/" + username + "/cart/" + menuItemId,
                type: "DELETE",
                data: {
                    menuItemId: menuItemId
                },
                success: function () {
                    window.location.reload(true);
                },
                error: function () {
                    alert("Error while deleting menu item from cart");
                }
            })
        })
    })
}

function setPurchaseEvent() {
    $("#purchase").click(function () {
        var username = $(this).attr("data-username");
        $.ajax({
            url: "/restaurant/users/" + username + "/cart/purchase",
            success: function () {
                window.location.reload(true);
            },
            error: function () {
                alert("Error while purchasing shopping cart");
            }
        })
    })
}
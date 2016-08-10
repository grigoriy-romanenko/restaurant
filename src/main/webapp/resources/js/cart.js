$(document).ready(function () {
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
                error: function () {
                    alert("Error while deleting menu item from cart");
                }
            })
        })
    })
});
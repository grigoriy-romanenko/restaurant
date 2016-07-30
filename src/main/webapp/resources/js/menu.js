$(document).ready(function () {
    setMenuItems();
    setCategories();
});

function setCategories() {
    $.ajax({
        url: "/restaurant/menu/categories",
        success: function(response) {
            var categories = $.map(response, function (category) {
                return new Option(category.title, category.id);
            });
            $("#categories").append(categories);
        },
        error: function() {
            console.error("Error while getting menu categories");
        }
    });
}

function setMenuItems() {
    $("#categories").change(function () {
        $("#items").empty();
        var categoryId = $("#categories").val();
        $.ajax({
            url: "/restaurant/menu/categories/" + categoryId + "/items",
            success: function(response) {
                var itemsList = $.map(response, function (item) {
                    return "<li>" + item.title + " " + item.price + "</li>";
                });
                $("#items").append("<ul>" + itemsList + "</ul>");
            },
            error: function() {
                console.error("Error while getting menu items");
            }
        });
    });
}


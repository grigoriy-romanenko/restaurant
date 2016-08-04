$(document).ready(function () {
    setCategories();
    createMenuItemPopupSetup();
});

function setCategories() {
    $.ajax({
        url: "/restaurant/menu/categories",
        success: function (response) {
            var categories = $.map(response, function (category) {
                return new Option(category.title, category.id);
            });
            $("#categories").append(categories);
            setMenuItems();
            $("#categories").change();
        },
        error: function () {
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
            success: function (response) {
                var itemsList = $.map(response, function (item) {
                    return createRowInItemsTable(item);
                });
                $("#items").append(itemsList);
            },
            error: function () {
                console.error("Error while getting menu items");
            }
        });
    });
}

function createRowInItemsTable(item) {
    var tr = document.createElement("tr");
    var titleCell = document.createElement("td");
    titleCell.textContent = item.title;
    tr.appendChild(titleCell);
    var priceCell = document.createElement("td");
    priceCell.textContent = item.price;
    tr.appendChild(priceCell);
    var actionsCell = document.createElement("td");
    var addToCartButton = document.createElement("button");
    addToCartButton.textContent = "Add to cart";
    addToCartButton.className = "btn btn-default";
    addToCartButton.onclick = function () {
        alert(item.id)
    };
    actionsCell.appendChild(addToCartButton);
    tr.appendChild(actionsCell);
    return tr;
}

function createMenuItemPopupSetup() {
    var dialog = $("#createMenuItemPopup").dialog({
        autoOpen: false
    });
    $("#submitMenuItem").click(function () {
        var categoryId = $("#categories").val();
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
                $("#categories").change();
            },
            error: function () {
                alert("Error while creating menu item");
            }
        });
    });
    $("#createMenuItemButton").on("click", function () {
        dialog.dialog("open");
    });
}
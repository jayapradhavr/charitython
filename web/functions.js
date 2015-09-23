/**
 * Created by bootcamp on 9/18/15.
 */
function sendAjax() {

    // get inputs
    var transaction = new Object();
    transaction.Name = "Amit Barve"
    transaction.Contact = "9595036117";
    transaction.Item = "rice";
    transaction.quantity = "3KG";
    transaction.emp_Id = "tempid@paypal.com";

    $.ajax({
        url: "http://localhost:8080/addData",
        type: 'POST',
        dataType: 'json',
        data:{
            loadProds: 1,
            transaction: JSON.stringify(transaction)
        },
        //contentType: 'application/json',
        //mimeType: 'application/json',

        success: function (data) {
            alert("Data sent successfully");
            //$("tr:has(td)").remove();
            //
            //$.each(data, function (index, article) {
            //
            //    var td_categories = $("<td/>");
            //    $.each(article.categories, function (i, tag) {
            //        var span = $("<span class='label label-info' style='margin:4px;padding:4px' />");
            //        span.text(tag);
            //        td_categories.append(span);
            //    });
            //
            //    var td_tags = $("<td/>");
            //    $.each(article.tags, function (i, tag) {
            //        var span = $("<span class='label' style='margin:4px;padding:4px' />");
            //        span.text(tag);
            //        td_tags.append(span);
            //    });
            //
            //    $("#added-articles").append($('<tr/>')
            //            .append($('<td/>').html("<a href='"+article.url+"'>"+article.title+"</a>"))
            //            .append(td_categories)
            //            .append(td_tags)
            //    );
            //
            //});
        },
        error:function(data,status,er) {
            alert("error: "+data+" status: "+status+" er:"+er);
        }
    });
}
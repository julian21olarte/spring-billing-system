$(document).ready(function() {
  $('#search_product').autocomplete({

    source: function(request, response) {
      $.ajax({
        type: "get",
        url: "/bill/search-product/" + request.term,
        dataType: "json",
        data: {
          term: request.term
        },
        success: function (data) {
          response(data.map(item => {
            return {
              value: item.id,
              label: item.name,
              price: item.price
            };
          }));
        }
      });
    },



    select: function(event, ui) {
      //$('#search_product').val(ui.item.label);
      var table = $('#load-items-bill tbody');
      var id = ui.item.value;
      var auxRow = table.find('tr#row_' + id);
      if(auxRow.length) { //if already exists
        var quantityField = $(auxRow).find('#quantity_' + id);
        var quantity = quantityField.val();
        quantityField.val(Number(quantity) + 1);
      } else {
        var line = $('#items-bill-body').html();
        line = line.replace(/{ID}/g, ui.item.value);
        line = line.replace(/{NAME}/g, ui.item.label);
        line = line.replace(/{PRICE}/g, ui.item.price);
        $('#load-items-bill tbody').append(line);
      }
      return false;
    }
  });
});
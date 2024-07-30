$(document).ready(function() {
    function updateTotalPrice(element) {
        var $item = element.closest('.item');
        var pricePerUnit = parseFloat($item.find('.price').text().replace('$', ''));
        var quantity = parseInt($item.find('.num').text());
        var totalPrice = pricePerUnit * quantity;
        $item.find('.total-price').text('$' + totalPrice.toFixed(2));
        updateTotalAmount();
    }

    function updateTotalAmount() {
        var totalAmount = 0;
        $('.item').each(function() {
            var totalPrice = parseFloat($(this).find('.total-price').text().replace('$', ''));
            totalAmount += totalPrice;
        });
        $('#total-amount').text('$' + totalAmount.toFixed(2));
    }

    $('.plus').on('click', function() {
        var $this = $(this);
        var $num = $this.siblings('.num');
        var value = parseInt($num.text());

        if (value < 100) {
            value++;
            value = (value < 10) ? "0" + value : value;
            $num.text(value);
            updateTotalPrice($this);
        }
    });

    $('.minus').on('click', function() {
        var $this = $(this);
        var $num = $this.siblings('.num');
        var value = parseInt($num.text());

        if (value > 1) {
            value--;
            value = (value < 10) ? "0" + value : value;
            $num.text(value);
            updateTotalPrice($this);
        }
    });

    $('.delete-btn').on('click', function() {
        var $this = $(this);
        $this.closest('.item').remove();
        updateTotalAmount();
    });

    // Initial total amount calculation
    updateTotalAmount();
});

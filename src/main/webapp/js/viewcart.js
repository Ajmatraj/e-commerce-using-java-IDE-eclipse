document.addEventListener('DOMContentLoaded', () => {
    const thumbnails = document.querySelectorAll('.thumbnail-images img');
    const mainImage = document.querySelector('.product-image img');

    thumbnails.forEach(thumbnail => {
        thumbnail.addEventListener('click', () => {
            mainImage.src = thumbnail.src;
            mainImage.alt = thumbnail.alt;
        });
    });
});



// * Demo purposes only */
var snippet = [].slice.call(document.querySelectorAll('.hover'));
if (snippet.length) {
  snippet.forEach(function (snippet) {
    snippet.addEventListener('mouseout', function (event) {
      if (event.target.parentNode.tagName === 'figure') {
        event.target.parentNode.classList.remove('hover')
      } else {
        event.target.parentNode.classList.remove('hover')
      }
    });
  });
}
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//Slide
//var slideIndex = 1;
//showSlides(slideIndex);
//
//function plusSlides(n) {
//    showSlides(slideIndex += n);
//}
//
//function currentSlide(n) {
//    showSlides(slideIndex = n);
//}
//
//function showSlides(n) {
//    var i;
//    var slides = document.getElementsByClassName("mySlides");
//    var dots = document.getElementsByClassName("dot");
//    if (n > slides.length) {
//        slideIndex = 1;
//    }
//    if (n < 1) {
//        slideIndex = slides.length;
//    }
//    for (i = 0; i < slides.length; i++) {
//        slides[i].style.display = "none";
//    }
//    for (i = 0; i < dots.length; i++) {
//        dots[i].className = dots[i].className.replace(" active", "");
//    }
//    slides[slideIndex - 1].style.display = "block";
//    dots[slideIndex - 1].className += " active";
//}

//Bar
window.onscroll = function () {
    slideSticky();
};

var navbar = document.getElementById("navbar");
var sticky = navbar.offsetTop;

function slideSticky() {
    if (window.pageYOffset >= sticky) {
        navbar.classList.add("sticky");
    } else {
        navbar.classList.remove("sticky");
    }
}
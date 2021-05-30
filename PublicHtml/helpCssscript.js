
function jsLiteraHoverN(num) {
  let numUp = num - 1;
  let numDown = num + 1;
  let classUpName = "sabLiteraInFlex-" + numUp;
  let classDownName = "sabLiteraInFlex-" + numDown;
  let classUp = document.getElementsByClassName(classUpName);
  let classDown = document.getElementsByClassName(classDownName);

  if(num > 0){
	  classUp[0].style.width = "50px";
    classUp[0].style.height = "50px";
    classUp[0].style.fontSize = "35px";
    classUp[0].style.left = "50px";
  }
  if(num < 25){
	  classDown[0].style.width = "50px";
    classDown[0].style.height = "50px";
    classDown[0].style.fontSize = "35px";
    classDown[0].style.left = "50px";
  }
}

function jsLiteraEndHoverN(num) {
  let numUp = num - 1;
  let numDown = num + 1;
  let classUpName = "sabLiteraInFlex-" + numUp;
  let classDownName = "sabLiteraInFlex-" + numDown;
  let classUp = document.getElementsByClassName(classUpName);
  let classDown = document.getElementsByClassName(classDownName);

  if(num > 0){
	  classUp[0].style.width = null;
    classUp[0].style.height = null;
    classUp[0].style.fontSize = null;
    classUp[0].style.left = null;
  }
  if(num < 25){
	  classDown[0].style.width = null;
    classDown[0].style.height = null;
    classDown[0].style.fontSize = null;
    classDown[0].style.left = null;
  }
}


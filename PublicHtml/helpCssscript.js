
function jsFnMarkBoxHoverN(num) {
  class3SetStyle(getClass(num));
  let iUpDown = classUpDown(num);
  if(num > 0) class2SetStyle(iUpDown.classUp);
  if(num < 25) class2SetStyle(iUpDown.classDown);
}
function jsFnMarkBoxEndHoverN(num) {
  classResetStyle(getClass(num));
  let iUpDown = classUpDown(num);
  if(num > 0) classResetStyle(iUpDown.classUp);
  if(num < 25) classResetStyle(iUpDown.classDown);
}
function classUpDown(num) {
  let classUpName = "sabLiteraInFlex-" + (num - 1);
  let classDownName = "sabLiteraInFlex-" + (num + 1);
  let iClUp = document.getElementsByClassName(classUpName);
  let iClDown = document.getElementsByClassName(classDownName);
  return {
    classUp: iClUp[0],
    classDown: iClDown[0],
  };
}
function getClass(num) {
  let className = "sabLiteraInFlex-" + num;
  let iClass = document.getElementsByClassName(className);
  return iClass[0];
}
function class3SetStyle(iclName) {
	  iclName.style.width = "65px";
    iclName.style.height = "65px";
    iclName.style.fontSize = "45px";
    iclName.style.left = "60px";
    iclName.style.zIndex = 2;
}
function class2SetStyle(iclName) {
	  iclName.style.width = "50px";
    iclName.style.height = "50px";
    iclName.style.fontSize = "35px";
    iclName.style.left = "50px";
}
function classResetStyle(iclName) {
	  iclName.style.width = null;
    iclName.style.height = null;
    iclName.style.fontSize = null;
    iclName.style.left = null;
    iclName.style.zIndex = null;
}

function jsLiteraHoverN(num) {
  class3SetStyle(getClass(num));
  let iUpDown = classUpDown(num);
  if(num > 0) class2SetStyle(iUpDown.classUp);
  if(num < 25) class2SetStyle(iUpDown.classDown);
}

function jsLiteraEndHoverN(num) {
  classResetStyle(getClass(num));
  let iUpDown = classUpDown(num);
  if(num > 0) classResetStyle(iUpDown.classUp);
  if(num < 25) classResetStyle(iUpDown.classDown);
}


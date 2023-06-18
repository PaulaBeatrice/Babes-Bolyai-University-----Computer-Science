const tabel = $("#tabel");
tabel.empty();
let values = [1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8];
let imgString = ["1.png", "2.png", "3.png", "4.png", "5.png", "6.png", "7.png", "8.png"];

// amestecarea valorilor 
for (let i = values.length - 1; i > 0; i--) {
  const j = Math.floor(Math.random() * (i + 1));
  [values[i], values[j]] = [values[j], values[i]];
}

const dim = Math.sqrt(values.length);
for (let i = 0; i < dim; i++) {
  const row = $("<tr></tr>");
  for (let j = 0; j < dim; j++) {
    const item = $("<td></td>");
    item.text(values[dim * i + j]);
    item.css("background-color", "black");
    item.attr("data-row", i);
    item.attr("data-cell", j);
    row.append(item);
    item.click(() => checkItems(item, imgString));
  }
  tabel.append(row);
}

let firstItem = null;
let secondItem = null;

function checkItems(item, imgString) {
  if (item.css("background-color") === "rgb(255, 255, 255)") {
    return; //deja descoperita
  }

  item.css("background-color", "white");
  const i = item.attr("data-row");
  const j = item.attr("data-cell");
  const index = item.text();
  item.css("background-image", `url(${imgString[index - 1]})`);

  if (firstItem === null) {
    firstItem = item;
  } else if (secondItem === null) {
    secondItem = item;

    //verificam daca se potrivesc
    if (firstItem.text() === secondItem.text()) {
      // se potrivesc
      firstItem = null;
      secondItem = null;
    } else {
      // nu se potrivesc
      setTimeout(() => {
        firstItem.css("background-color", "black");
        secondItem.css("background-color", "black");
        firstItem.css("background-image", "");
        secondItem.css("background-image", "");
        firstItem = null;
        secondItem = null;
      }, 2000);
    }
  }
}

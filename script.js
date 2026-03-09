// ================= LOGIN PAGE =================

let loginForm = document.getElementById("loginForm");

if (loginForm) {

loginForm.addEventListener("submit", function (e) {

e.preventDefault();

let name = document.getElementById("name").value.trim();
let phone = document.getElementById("phone").value.trim();

if (!/^\d{10}$/.test(phone)) {
alert("Enter valid 10 digit phone number");
return;
}

sessionStorage.setItem("farmerName", name);

window.location.href = "recommendation.html";

});

}



// ================= SHOW USER NAME =================

let welcome = document.getElementById("welcomeUser");

if (welcome) {

let name = sessionStorage.getItem("farmerName") || "Farmer";

welcome.innerText =
`Hello, ${name}! Tell us about your field and we'll recommend the right fertilizer.`;

}



// ================= FERTILIZER DATABASE =================

const fertilizerDB = {

potato: {

flowering: [

{
type: "K Major",
fertilizer: "Muriate of Potash (MOP)",
reason:
"Provides potassium which improves tuber bulking and strengthens plant resistance."
},

{
type: "Foliar",
fertilizer: "NPK 0-0-50 Spray",
reason:
"Supplies potassium directly to leaves for faster nutrient absorption and better tuber growth."
}

]

},

rice: {

vegetative: [

{
type: "Nitrogen",
fertilizer: "Urea",
reason:
"Nitrogen promotes leaf and tiller growth which increases photosynthesis."
},

{
type: "Micronutrient",
fertilizer: "Zinc Sulphate",
reason:
"Zinc helps enzyme activity and prevents yellowing of leaves."
}

]

},

wheat: {

vegetative: [

{
type: "Nitrogen",
fertilizer: "Urea",
reason:
"Helps wheat plants grow strong leaves and improves crop yield."
}

]

},

maize: {

vegetative: [

{
type: "Nitrogen",
fertilizer: "Urea",
reason:
"Improves leaf development and helps maize plants grow taller."
}

]

},

tomato: {

flowering: [

{
type: "Phosphorus",
fertilizer: "DAP (Di-Ammonium Phosphate)",
reason:
"Promotes flowering and improves fruit development."
}

]

}

};



// ================= SOIL NOTES =================

const soilNotes = {

clay:
"Clay soils retain water but drain slowly. Adding organic matter improves aeration.",

sandy:
"Sandy soils lose nutrients quickly, so fertilizers should be applied in smaller split doses.",

black:
"Black soils hold moisture well but may require phosphorus supplementation.",

loamy:
"Loamy soil is fertile and retains nutrients well, suitable for many crops.",

red:
"Red soils are often low in nitrogen and organic matter, so compost improves fertility."

};



// ================= RECOMMENDATION SYSTEM =================

let cropForm = document.getElementById("cropForm");

if (cropForm) {

cropForm.addEventListener("submit", function (e) {

e.preventDefault();

let crop = document.getElementById("crop").value;
let soil = document.getElementById("soil").value;
let stage = document.getElementById("stage").value;

if (!crop || !soil || !stage) {

alert("Please select crop, soil type, and growth stage");

return;

}

let recList = document.getElementById("recList");

recList.innerHTML = "";

let recs = fertilizerDB[crop]?.[stage];

if (!recs) {

recList.innerHTML =
"<p>No recommendation available for this selection.</p>";

}

else {

recs.forEach(r => {

recList.innerHTML += `

<div class="recCard">

<span class="badge">${r.type}</span>

<p>
<strong>Fertilizer:</strong> ${r.fertilizer}<br>
${r.reason}
</p>

</div>

`;

});

}

if (soilNotes[soil]) {

recList.innerHTML += `

<div class="soilNote">

<span class="soilBadge">Soil</span>

<p>${soilNotes[soil]}</p>

</div>

`;

}

document.getElementById("resultBox").style.display = "block";

});

}
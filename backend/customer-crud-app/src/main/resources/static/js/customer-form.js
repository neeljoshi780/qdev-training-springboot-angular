// ================================
// Customer Form Input Validations
// ================================

// Run script after DOM is fully loaded
document.addEventListener("DOMContentLoaded", function () {

  /* --------------------------------
   * Mobile Number Validation
   * - Allows only digits
   * - Maximum length: 10
   * -------------------------------- */
  const mobileInput = document.getElementById("mobile");

  if (mobileInput) {
    mobileInput.addEventListener("input", function () {

      // Remove any character that is not a digit (0–9)
      let value = this.value.replace(/[^0-9]/g, "");

      // Restrict input to 10 digits
      if (value.length > 10) {
        value = value.slice(0, 10);
      }

      // Update the input value
      this.value = value;
    });
  }

  /* --------------------------------
   * Age Validation
   * - Allows only digits
   * - Range: 1 to 120
   * -------------------------------- */
  const ageInput = document.getElementById("age");

  if (ageInput) {
    ageInput.addEventListener("input", function () {

      // Remove non-numeric characters
      let value = this.value.replace(/[^0-9]/g, "");

      // If input is cleared, allow empty value
      if (value === "") {
        this.value = "";
        return;
      }

      // Convert value to number
      let num = parseInt(value, 10);

      // Enforce minimum age
      if (num < 1) {
        this.value = "1";
      }
      // Enforce maximum age
      else if (num > 120) {
        this.value = "120";
      }
      // Valid age value
      else {
        this.value = num;
      }
    });
  }

});
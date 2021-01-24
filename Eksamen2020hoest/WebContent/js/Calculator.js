
'use strict'

class Calculator {
	constructor(status = "", result = "") {
		this._status = status;
		this._result = result;
	}

	/**
	* Metode for å utføre utregning
	* @public
	* @param {String} operation - Matematikk operasjon
	* @param {Array.<String>} numberList - Array av input-data
	*/
	calculate(operation, numberList) {
		this.result = 0;
		let harKunHeltall = true;
		let antallIkkeHeltall = 0;

		// status
		numberList.forEach(n => {
			if (/^-?\d+$/.test(n) === false) {
				harKunHeltall = false;
				antallIkkeHeltall++;
			}
		});

		if (antallIkkeHeltall === numberList.length || numberList.length === 0) {
			this.status = 'Ingen tall i tallisten';
		} else if (harKunHeltall === false) {
			this.status = 'Tallisten inneholder verdi(er) som ikke er tall';
		} else {
			this.status = 'Alle input-verdier ble prosessert';
		}

		// result
		if (operation === 'sum') {
			numberList.forEach((n) => {
				if (!isNaN(parseInt(n))) {
					this.result += parseInt(n);
				}
			});
		} else if (operation === 'produkt') {
			numberList.forEach((n, i) => {
				if (!isNaN(parseInt(n))) {
					if (i === 0) {
						this.result = n;
					} else {
						this.result *= parseInt(n);
					}
				}
			});
		} else if (operation === 'min') {
			this.result = Math.min(...numberList);
		}
	}

	get status() {
		return this._status;
	}
	set status(status) {
		this._status = status;
	}

	get result() {
		return this._result;
	}
	set result(result) {
		this._result = result;
	}
}

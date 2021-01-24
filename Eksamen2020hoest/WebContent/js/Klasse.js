
class Klasse {
	constructor(prop = "") {
		this._prop = prop;
	}
	
	get prop() {
		return this._prop;
	}
	
	set prop(prop) {
		this._prop = prop;
	}
}

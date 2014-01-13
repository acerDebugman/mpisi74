/**
 * @author hsong
 */
function Decimal(primitiveValue, digit, isInternalMode)
{
	this.iDigit=Number.NaN;
	this.iNumber=Number.NaN;

	this.imDigit=Number.NaN;
	this.imNumber=Number.NaN;

	var fNumber;
	switch (typeof(primitiveValue) ) {
		case "number":
			if (isInternalMode) {
				this.iNumber=primitiveValue.toFixed(0);
				this.iDigit=digit;
				return;
			}
			fNumber=primitiveValue;
			break;	
		case "string": 
			fNumber=parseFloat(primitiveValue);
			break;
		case "undefined":
			return;
		default:
			throw "Invalid parameter to create an Decimal object, a string or number expected!";
	}

	var sExponential;
	if (digit) {
		sExponential=fNumber.toExponential(digit);	
	} else {
		sExponential=fNumber.toExponential();
	}
	var numberParts=sExponential.split("e");
	var sNumber=numberParts[0];
	var iDigit=-parseInt(numberParts[1],10);
	
	var iPointPos=sNumber.indexOf(".");
	if (iPointPos>=0) {
		iDigit+=sNumber.length-iPointPos-1;
	}
	if (iDigit<0) {
		iNumber=fNumber;
		iDigit=0;
	} else {
		sNumber=sNumber.replace(".","");
		iNumber=parseInt(sNumber, 10);
	}
	this.iNumber=iNumber;
	this.iDigit=iDigit;
}

Decimal.prototype.valueOf=function() 
{
	return parseFloat(this.toString());
};

Decimal.prototype.toString=function() 
{
	if (isNaN(this.iNumber) || isNaN(this.iDigit)) {
		return "NaN";
	}
	var sNumber=this.iNumber.toString();
	var digit=this.iDigit;
	if (digit==0) {
		return sNumber;
	}
	var len=sNumber.length;
	return sNumber.substring(0, len-digit).concat( ".",sNumber.substring(len-digit, len) );
};

Decimal.prototype.add=function(operand)
{
	var operDec = new Decimal(operand);
	var iDigit=this._makeSameDigit(operDec);
	return new Decimal(this.imNumber+operDec.imNumber, iDigit, true);	
};

Decimal.prototype.minus=function(operand)
{
	var operDec = new Decimal(operand);
	var iDigit=this._makeSameDigit(operDec);
	return new Decimal(this.imNumber-operDec.imNumber, iDigit, true);	
};

Decimal.prototype.multiply=function(operand)
{
	var operDec = new Decimal(operand);
	var iDigit=this._makeSameDigit(operDec);
	return new Decimal(this.imNumber*operDec.imNumber, iDigit<<1, true);	
};

Decimal.prototype.divide=function(operand)
{
	var operDec = new Decimal(operand);
	var iDigit=this._makeSameDigit(operDec);
	return new Decimal(this.imNumber/operDec.imNumber);	
};

Decimal.prototype._makeSameDigit=function(oDecimal)
{
	return _makeSameDigit([this,oDecimal]);
};

Decimal.prototype._setIntNumber=function(iNumber)
{
	this.iNumber=iNumber;
};

Decimal.prototype._setDigit=function(iDigit)
{
	this.iDigit=iDigit;
};

function _makeSameDigit(decimals)
{
	var iMaxDigit=0;
	for (var len=decimals.length, i=0;i<len;i++) {
		var decimal=decimals[i];
		if (decimal.iDigit>=iMaxDigit) {
			iMaxDigit=decimal.iDigit;
		}
	}
	for (var len=decimals.length, i=0;i<len;i++) {
		var decimal=decimals[i];
		decimal.imNumber=decimal.iNumber*Math.pow(10,iMaxDigit-decimal.iDigit);
		decimal.imDigit=iMaxDigit;
	}
	return iMaxDigit;
}


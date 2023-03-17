package Model;

public class NumarComplex {
    private int re;
    private int im;

    public NumarComplex(int re, int im)
    {
        this.re = re;
        this.im = im;
    }

    public int getRe() {
        return re;
    }

    public int getIm() {
        return im;
    }

    public void setRe(int re)
    {
        this.re = re;
    }

    public NumarComplex adunare(NumarComplex nr)
    {
        return new NumarComplex(this.re + nr.getRe(), this.im + nr.getIm());
    }

    public NumarComplex scadere(NumarComplex nr)
    {
        return new NumarComplex(this.re - nr.re, this.im - nr.im);
    }

    public NumarComplex inmultire(NumarComplex nr)
    {
        int re = this.re * nr.re - this.im * nr.im;
        int im = this.re * nr.im + this.im * nr.re;
        return new NumarComplex(re, im);
    }

    public NumarComplex impartire(NumarComplex nr)
    {
        int re = (this.re * nr.re + this.im * nr.im) / (nr.re * nr.re + nr.im * nr.im);
        int im = (this.im * nr.re - this.re * nr.im) / (nr.re * nr.re + nr.im * nr.im);
        return new NumarComplex(re, im);
    }

    public NumarComplex conjugatul()
    {
        return new NumarComplex(this.re,(-1) * this.im);
    }

    public String toString() {return "("+re+", "+im+")";}
}

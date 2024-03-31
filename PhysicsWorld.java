// import java.util.Arrays;

class Point {
    private float x;
    private float y;
    private float norme;

    public Point(float xCoor, float yCoor) {
        this.x = xCoor;
        this.y = yCoor;
    }

    // Pour obtenir le x d'un point
    public float getX() {
        return this.x;
    }

    // Pour obtenir le y d'un point
    public float getY() {
        return this.y;
    }

    // Pour obtenir la norme d'un point
    public float getNorme() {
        return this.norme;
    }

    // Pour modifier la valeur du x d'un point
    public void setX(float newX) {
        this.x = newX;
        this.setNorme();
    }

    // Pour modifier la valeur du y d'un point
    public void setY(float newY) {
        this.y = newY;
        this.setNorme();
    }

    // pour recalculer la norme d'un vecteur après modification des composantes
    public void setNorme() {
        this.norme = (float) Math.sqrt(this.x * this.x + this.y * this.y);
    }
}

class Vector extends Point {
    private float xStart;
    private float yStart;

    // Le constructeur du vecteur
    public Vector(float xCoor, float yCoor) {
        super(xCoor, yCoor);
        this.setNorme();
        xStart = 0;
        yStart = 0;
    }

    public Vector(Point p) {
        super(p.getX(), p.getY());
        this.setNorme();
        xStart = 0;
        yStart = 0;
    }

    // Pour modifier la valeur du x d'un point
    public void setX(float newX) {
        this.xStart = newX;
        this.setNorme();
    }

    // Pour modifier la valeur du y d'un point
    public void setY(float newY) {
        this.yStart = newY;
        this.setNorme();
    }

    public void add(Vector v) {
        this.setX(this.getX() + v.getX());
        this.setX(this.getY() + v.getY());
        this.setNorme();
    }

    public void substract(Vector v) {
        this.setX(this.getX() - v.getX());
        this.setX(this.getX() - v.getY());
        this.setNorme();
    }

    public void multiply(float k) {
        this.setX(this.getX() * k);
        this.setX(this.getX() * k);
        this.setNorme();
    }

    public void divide(float k) {
        this.setX(this.getX() / k);
        this.setX(this.getX() / k);
        this.setNorme();
    }

    // opération vectorielle
    public float det(Vector u) {
        return (this.getX() * u.getY()) - (this.getY() * u.getX());
    }

    public float scalar(Vector v) {
        Vector u = new Vector(this.getX(), this.getY());
        u.substract(v);
        return (float) (this.getNorme() * this.getNorme() + v.getNorme() * v.getNorme() - u.getNorme() * u.getNorme());
    }

    // champs vectoriels
    public Vector gravityField(float x, float y) {
        return (new Vector(9.8f / ((x + 100) * (x + 100)), (9.8f / ((x + 100) * (x + 100)))));
    }
}

class Polygon {
    private Vector[] lPts;

    public Polygon(Vector[] listPoints) {
        this.lPts = listPoints;
    }

    public Vector[] getLPts() {
        return this.lPts;
    }

    public float surface() {
        float surface = 0;
        for (int i = 0; i < this.lPts.length; i++) {
            int j = (i + 1) % this.lPts.length;
            surface += this.lPts[i].getX() * this.lPts[i].getY() - this.lPts[j].getX() * this.lPts[j].getY();
        }
        return (float) (Math.abs(surface) / 2.0);
    }
}

class Object {
    Vector position;
    Vector velocity;
    Vector force; // force/mass = acceleration = velocity/time
    Vector acceleration;
    float mass;

    // Les arguments (dans l'ordre) sont: position, velocité, force, masse.
    public Object(Vector p, Vector v, Vector f, float m) {
        this.position = p;
        this.velocity = v;
        this.force = f;
        this.mass = m;
    }

    public void setAcceleration(Vector a) {
        this.acceleration = a;
    }

    public Vector calculVecteurAcceleration(Object o) {
        return (new Vector((float) o.force.getX() / o.mass, (float) o.force.getY() / o.mass));
    }
}

public class PhysicsWorld {

    // La fonction main est appelée quand le programme se lance
    public static void main(String[] args) {

        Object testObj = new Object(new Vector(5, 3), new Vector(3, 0), new Vector(0, 1), 1);

        System.out.println("position.x = " + testObj.position.getX());
        System.out.println("force.y = " + testObj.force.getY());

        testObj.velocity.setX(69);
        System.out.println(testObj.velocity.getX());
        testObj.position.setY(420);
        System.out.println(testObj.position.getY());
    }
}
package pl.bobkins;

import pl.bobkins.polar.Polar2DAdapter;
import pl.bobkins.vector.vector2d.Vector2D;
import pl.bobkins.vector.vector2d.Vector2DPolarInheritance;
import pl.bobkins.vector.vector3d.Vector3DDecorator;
import pl.bobkins.vector.vector3d.Vector3DInheritance;

public class Main {
    public static void main(String[] args) {

        Vector2D vector1 = new Vector2D(4, 6);
        Polar2DAdapter polar2DAdapter = new Polar2DAdapter(vector1);

        Vector2DPolarInheritance vector2DPolarInheritance = new Vector2DPolarInheritance(-2, 3);

        Vector3DInheritance vector3DInheritance = new Vector3DInheritance(-1, 2, 5);
        // Wada - Można zastosować vector 3D inheritance do dekorowania, a wtedy sypią się współrzędne.
        Vector3DDecorator vector3DDecorator = new Vector3DDecorator(vector2DPolarInheritance, 3);

        // Współrzędne wektorów
        System.out.println(polar2DAdapter);
        System.out.println(vector2DPolarInheritance);
        System.out.println(vector3DInheritance);
        System.out.println(vector3DDecorator);

        // Iloczyny skalarne wektorów
        System.out.println(polar2DAdapter.cdot(vector2DPolarInheritance));
        System.out.println(polar2DAdapter.cdot(vector3DInheritance));
        System.out.println(polar2DAdapter.cdot(vector3DDecorator));

        System.out.println(vector2DPolarInheritance.cdot(vector3DDecorator));
        System.out.println(vector2DPolarInheritance.cdot(vector3DInheritance));

        System.out.println(vector3DInheritance.cdot(vector3DDecorator));

        // Iloczyny wektorowe
        System.out.println(vector3DDecorator.cross(polar2DAdapter));
        System.out.println(vector3DDecorator.cross(vector2DPolarInheritance));
        System.out.println(vector3DDecorator.cross(vector3DInheritance));

        System.out.println(vector3DInheritance.cross(polar2DAdapter));
        System.out.println(vector3DInheritance.cross(vector2DPolarInheritance));

    }
}
;
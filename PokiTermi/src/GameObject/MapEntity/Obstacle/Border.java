package GameObject.MapEntity.Obstacle;

import GameObject.MapEntity.Coordinate.Coordinate;

public class Border extends Coordinate {

    public Border(int row, int col) {
        super(row, col);
    }

    @Override
    public void display() {
        super.display();

        if(this.getCol()==0&&this.getRow()==0){
            System.out.print("\u250C");// ┌ Left top corner
            return;
        } else if (this.getCol()==0&&this.getRow()==tableRows-1) {
            System.out.print("\u2514");// └ Left bottom corner
            return;
            
        } else if (this.getCol()==tableColumns-1&&this.getRow()==0) {
            System.out.print("\u2510");  // ┐ Right top corner
            return;

        }else if (this.getCol()==tableColumns-1&&this.getRow()==tableRows-1) {
            System.out.print("\u2518");  // ┘ Right bottom corner
            return;
        }
        if(this.getRow()==0||this.getRow()==tableRows-1){
            System.out.print("\u2550");
        }else if(this.getCol()==0||this.getCol()==tableColumns-1){
            System.out.print("\u2551");
        }
    }
}

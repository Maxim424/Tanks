import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Random;

public class Map {


    private static int [][] matrix;
    public static final int WORLD_SIZE = 60;

    public static final int EMPTY  = 0;
    public static final int BLOCK_SIZE = 32;

    public static final int BLOCK_LAYER = 0;
    public static final int GROUND = 1;
    public static final int WATER  = 2;
    public static final int BRICK  = 3;
    public static final int WALL   = 4;

    public static final int OBJECT_LAYER = 1;
    public static final int BULLET_1   = 5;
    public static final int BULLET_3   = 6;

    private static Random r = new Random();
    private Camera camera = Camera.getInstance();
    private static Map instance = null;

    private Map() {
        createWorld(WORLD_SIZE,WORLD_SIZE);
    }

    public static Map getInstance() {
        if (instance == null) {
            instance = new Map();
        }
        return instance;
    }

    public int getBlock (int r, int c) {
        return get(matrix[r][c], BLOCK_LAYER);
    }

    public void spawnBlock (double xW, double yW , int block) {

        int row = getRowByY(yW);
        int col = getColByX(xW);
        matrix[row][col] = set(matrix[row][col], BLOCK_LAYER, block);
    }

    public void spawnObject (double xW, double yW , int object) {
        int row = getRowByY(yW);
        int col = getColByX(xW);
        matrix[row][col] = set(matrix[row][col], OBJECT_LAYER, object);
    }

    public void createWorld (int rows, int cols) {
        matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = GROUND;
            }
        }

        for (int i = 0; i < rows; i++) {
            matrix[i][0] = set(matrix[i][0], BLOCK_LAYER, WATER);
            matrix[i][cols-1] = set(matrix[i][cols-1], BLOCK_LAYER, WATER);

        }
        for (int i = 0; i < cols; i++) {
            matrix[0][i] = set(matrix[0][i], BLOCK_LAYER, WATER);
            matrix[rows-1][i] = set(matrix[rows-1][i], BLOCK_LAYER, WATER);
        }

        for (int i = 1; i < rows-1; i++) {
            for (int j = 1; j < cols-1; j++) {
                if (get(matrix[i][j-1], BLOCK_LAYER)==WATER || get(matrix[i-1][j], BLOCK_LAYER)==WATER) {
                    int block = r.nextInt(2);
                    if (block==1) {
                        matrix[i][j] = set(matrix[i][j], BLOCK_LAYER, WATER);
                    }
                    else {
                        matrix[i][j] = set(matrix[i][j], BLOCK_LAYER, GROUND);
                    }

                }
                else {
                    int block = r.nextInt(11);
                    if (block<=8) {
                        matrix[i][j] = set(matrix[i][j], BLOCK_LAYER, GROUND);
                    }
                    else {
                        block = r.nextInt(2);
                        if (block==0) {
                            matrix[i][j] = set(matrix[i][j], BLOCK_LAYER, BRICK);
                        }
                        else {
                            matrix[i][j] = set(matrix[i][j], BLOCK_LAYER, WALL);
                        }

                    }


                }
            }
        }

        for (int i = rows-2; i >=rows/3; i--) {
            for (int j = cols-2; j >=cols/3; j--) {
                if (get(matrix[i][j+1], BLOCK_LAYER) == WATER || get(matrix[i+1][j], BLOCK_LAYER) == WATER) {
                    int block = r.nextInt(2);
                    if (block==1) {
                        matrix[i][j] = set(matrix[i][j], BLOCK_LAYER, WATER);
                    }
                    else {
                        matrix[i][j] = set(matrix[i][j], BLOCK_LAYER, GROUND);
                    }

                }
            }
        }

        spawnBlock(Map.BLOCK_SIZE*3, Map.BLOCK_SIZE*13, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*4, Map.BLOCK_SIZE*13, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*5, Map.BLOCK_SIZE*13, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*3, Map.BLOCK_SIZE*12, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*4, Map.BLOCK_SIZE*12, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*5, Map.BLOCK_SIZE*12, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*3, Map.BLOCK_SIZE*11, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*4, Map.BLOCK_SIZE*11, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*5, Map.BLOCK_SIZE*11, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*6, Map.BLOCK_SIZE*11, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*7, Map.BLOCK_SIZE*11, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*5, Map.BLOCK_SIZE*10, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*6, Map.BLOCK_SIZE*10, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*7, Map.BLOCK_SIZE*10, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*5, Map.BLOCK_SIZE*9, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*6, Map.BLOCK_SIZE*9, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*7, Map.BLOCK_SIZE*9, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*8, Map.BLOCK_SIZE*9, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*9, Map.BLOCK_SIZE*9, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*7, Map.BLOCK_SIZE*8, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*8, Map.BLOCK_SIZE*8, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*9, Map.BLOCK_SIZE*8, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*7, Map.BLOCK_SIZE*7, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*8, Map.BLOCK_SIZE*7, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*9, Map.BLOCK_SIZE*7, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*10, Map.BLOCK_SIZE*7, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*11, Map.BLOCK_SIZE*7, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*9, Map.BLOCK_SIZE*6, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*10, Map.BLOCK_SIZE*6, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*11, Map.BLOCK_SIZE*6, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*9, Map.BLOCK_SIZE*5, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*10, Map.BLOCK_SIZE*5, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*11, Map.BLOCK_SIZE*5, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*12, Map.BLOCK_SIZE*5, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*13, Map.BLOCK_SIZE*5, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*11, Map.BLOCK_SIZE*4, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*12, Map.BLOCK_SIZE*4, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*13, Map.BLOCK_SIZE*4, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*11, Map.BLOCK_SIZE*3, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*12, Map.BLOCK_SIZE*3, Map.GROUND);
        spawnBlock(Map.BLOCK_SIZE*13, Map.BLOCK_SIZE*3, Map.GROUND);


        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*3, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*13, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*4, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*13, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*5, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*13, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*3, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*12, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*4, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*12, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*5, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*12, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*3, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*11, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*4, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*11, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*5, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*11, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*6, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*11, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*7, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*11, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*5, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*10, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*6, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*10, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*7, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*10, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*5, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*9, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*6, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*9, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*7, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*9, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*8, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*9, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*9, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*9, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*7, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*8, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*8, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*8, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*9, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*8, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*7, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*7, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*8, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*7, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*9, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*7, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*10, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*7, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*11, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*7, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*9, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*6, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*10, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*6, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*11, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*6, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*9, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*5, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*10, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*5, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*11, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*5, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*12, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*5, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*13, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*5, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*11, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*4, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*12, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*4, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*13, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*4, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*11, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*3, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*12, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*3, Map.GROUND);
        spawnBlock(Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*13, Map.WORLD_SIZE*Map.BLOCK_SIZE - Map.BLOCK_SIZE*3, Map.GROUND);


    }

    public void destroyBlock(double x, double y,  int block) {
        if (block==Map.BRICK) {
            spawnBlock(x, y, Map.GROUND);
        }

    }


    public int getColByX (double X) {
        return (int)X / BLOCK_SIZE;
    }
    public int getRowByY (double Y) {
        return (int)Y / BLOCK_SIZE;
    }

    public void paint (Graphics g) {

        int row1, col1, row2, col2;
        row1 = getRowByY(camera.getTop());
        col1 = getColByX(camera.getLeft());

        row2 = getRowByY(camera.getBottom());
        col2 = getColByX(camera.getRight());


        int screenX, screenY, block;
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <=  col2; j++) {
                block = matrix[i][j];
                screenX = camera.getScreenX(j * Map.BLOCK_SIZE);
                screenY = camera.getScreenY(i * Map.BLOCK_SIZE);
                ImageHelper.paint(g, get(block, BLOCK_LAYER), screenX, screenY);
                ImageHelper.paint(g, get(block, OBJECT_LAYER), screenX, screenY);
            }
        }

    }

    public int  clear (int cell, int layer) {
        int mask = 255 << layer * 8;
        mask = ~mask;
        return cell & mask;
    }

    public int set (int cell, int layer, int code) {
        cell = clear(cell, layer);
        int mask = code << layer * 8;
        return cell | mask;
    }

    public int get (int cell, int layer) {
        int mask = 255 << layer * 8;
        cell = cell & mask;
        return cell >> layer * 8;
    }

    public void saveMatrix(int slot, String name) {
        File file = new File(((new File(".").getAbsolutePath())+"MapConfig" + slot + ".txt"));
        try{
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        try(FileWriter writer = new FileWriter (file)) {
            writer.write(name);
            writer.append('\n');
            int block;
            for (int i = 0; i < WORLD_SIZE; i++) {
                for (int j = 0; j < WORLD_SIZE; j++) {
                    block = get(matrix[i][j], 0);
                    writer.write(block + " ");
                }
                writer.append('\n');
            }
            writer.flush();
            System.out.println("Successfully saved!");
        }
        catch (IOException ex){
            System.out.println("Map saving failed.");
            System.out.println(ex.getMessage());
        }
    }

    public String GetSlotName(int slot){
        File file = new File(((new File(".").getAbsolutePath())+"MapConfig" + slot + ".txt"));
        if(file.exists()){
            BufferedReader objReader = null;
            try {
                objReader = new BufferedReader(new FileReader(file));
                return objReader.readLine();
            } catch(IOException ex){
                System.out.println("Getting map name failed.");
                ex.printStackTrace();
                return null;
            } finally {
                try {
                    if (objReader != null)
                        objReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        else{
            return null;
        }
    }

    public void loadMatrix(int slot){
        File file = new File(((new File(".").getAbsolutePath())+"MapConfig" + slot + ".txt"));
        if(file.exists()) {
            int row = 0, col = 0;
            BufferedReader objReader = null;
            try {
                char c;
                String strCurrentLine;
                objReader = new BufferedReader(new FileReader(file));
                objReader.readLine();
                while ((strCurrentLine = objReader.readLine()) != null) {
                    for (int i = 0; i < strCurrentLine.length(); i++) {
                        c = strCurrentLine.charAt(i);
                        if (c == ' ') col++;
                        else matrix[row][col] = set(matrix[row][col], 0, (int) c - 48);
                    }
                    col = 0;
                    row++;
                }
                System.out.println("Map loaded!");
            } catch (IOException ex) {
                System.out.println("Map loading failed.");
                ex.printStackTrace();
            } finally {
                try {
                    if (objReader != null)
                        objReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Слот пустой");
        }
    }

    public void loadMatrixLevel(int slot){
        File file = new File(((new File(".").getAbsolutePath())+"Level" + slot + "Config.txt"));
        if(file.exists()) {
            int row = 0, col = 0;
            BufferedReader objReader = null;
            try {
                char c;
                String strCurrentLine;
                objReader = new BufferedReader(new FileReader(file));
                objReader.readLine();
                while ((strCurrentLine = objReader.readLine()) != null) {
                    for (int i = 0; i < strCurrentLine.length(); i++) {
                        c = strCurrentLine.charAt(i);
                        if (c == ' ') col++;
                        else matrix[row][col] = set(matrix[row][col], 0, (int) c - 48);
                    }
                    col = 0;
                    row++;
                }
                System.out.println("Map loaded!");
            } catch (IOException ex) {
                System.out.println("Map loading failed.");
                ex.printStackTrace();
            } finally {
                try {
                    if (objReader != null)
                        objReader.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Ошибка");
        }
    }

}

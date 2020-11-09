package gui;

import convertation.ConvertPlane;
import convertation.Converter;
import gui.eventlisteners.controlpanelevents.SizeEventHandler;
import gui.eventlisteners.derivativepainterevents.PolynomialHandler;
import gui.eventlisteners.derivativepainterevents.PolynomialSetHandler;
import gui.eventlisteners.footerevents.ColorDerListener;
import gui.eventlisteners.footerevents.ColorFuncListener;
import gui.eventlisteners.functionpainterevents.PolynomialEventHandler;
import gui.eventlisteners.mainpanelevents.functionpanelevents.SmoothEventHandler;
import gui.eventlisteners.mainpanelevents.rightpanelevents.DeleteNodeHandler;
import gui.eventlisteners.mainpanelevents.rightpanelevents.RemoveAllHandler;
import gui.eventlisteners.mainpanelevents.rightpanelevents.SelectEventHandler;
import gui.eventlisteners.upperpanelevents.*;
import gui.painters.CartesianPainter;
import gui.painters.DerivativePainter;
import gui.painters.FunctionPainter;
import gui.painters.PointPainter;
import gui.panels.controlpanel.ControlPanel;
import gui.panels.auxiliarypanel.AuxiliaryPanel;
import gui.panels.functionpanel.FunctionPanel;
import gui.panels.mainpanel.MainPanel;
import gui.panels.upperpanel.UpperPanel;
import gui.visualobjects.VPoint;
import polynomials.Newton;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.*;

public class MyWindow extends JFrame {
    private static final Dimension MIN_FRAME_SIZE = new Dimension(720, 720);
    MainPanel mainPanel;
    ControlPanel controlPanel;
    FunctionPanel functionPanel;
    UpperPanel upperPanel;
    AuxiliaryPanel footer;

    CartesianPainter painter;
    PointPainter pointPainter;
    FunctionPainter functionPainter;
    DerivativePainter derivativePainter;

    Point startPoint = new Point(0, 0);

    public MyWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setMinimumSize(MIN_FRAME_SIZE);
        setTitle("Polynomial Graphics");

        mainPanel = new MainPanel();
        controlPanel = new ControlPanel();
        functionPanel = new FunctionPanel();
        upperPanel = new UpperPanel();
        footer = new AuxiliaryPanel();

        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EtchedBorder());
        functionPanel.setBorder(new EtchedBorder());
        controlPanel.setBorder(new EtchedBorder());
        upperPanel.setBackground(Color.WHITE);
        upperPanel.setBorder(new EtchedBorder());

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGap(5)
                .addComponent(upperPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(5)
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
                .addComponent(functionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(5)
                .addComponent(footer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                .addGap(5)
                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(5)
        );
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGap(5)
                .addGroup(groupLayout.createParallelGroup()
                        .addGap(5)
                        .addComponent(upperPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(functionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(footer, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                        .addComponent(controlPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                        .addGap(5)
                )
                .addGap(5)
        );
        setLayout(groupLayout);
        pack();
        painter = new CartesianPainter(new ConvertPlane(mainPanel.graphicsPanel.getWidth(), mainPanel.graphicsPanel.getHeight(),
                controlPanel.getXMin(), controlPanel.getXMax(), controlPanel.getYMin(), controlPanel.getYMax()));
        mainPanel.graphicsPanel.addPainter(painter);
        pointPainter = new PointPainter(new ConvertPlane(mainPanel.graphicsPanel.getWidth(), mainPanel.graphicsPanel.getHeight(),
                controlPanel.getXMin(), controlPanel.getXMax(), controlPanel.getYMin(), controlPanel.getYMax()));
        mainPanel.graphicsPanel.addPainter(pointPainter);
        functionPainter = new FunctionPainter(new ConvertPlane(mainPanel.graphicsPanel.getWidth(), mainPanel.graphicsPanel.getHeight(),
                controlPanel.getXMin(), controlPanel.getXMax(), controlPanel.getYMin(), controlPanel.getYMax()));
        mainPanel.graphicsPanel.addPainter(functionPainter);
        derivativePainter = new DerivativePainter(new ConvertPlane(mainPanel.graphicsPanel.getWidth(), mainPanel.graphicsPanel.getHeight(),
                controlPanel.getXMin(), controlPanel.getXMax(), controlPanel.getYMin(), controlPanel.getYMax()));
        mainPanel.graphicsPanel.addPainter(derivativePainter);


        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                painter.update(mainPanel.graphicsPanel.getWidth(), mainPanel.graphicsPanel.getHeight());
                pointPainter.update(mainPanel.graphicsPanel.getWidth(), mainPanel.graphicsPanel.getHeight());
                functionPainter.update(mainPanel.graphicsPanel.getWidth(), mainPanel.graphicsPanel.getHeight());
                derivativePainter.update(mainPanel.graphicsPanel.getWidth(), mainPanel.graphicsPanel.getHeight());
                mainPanel.graphicsPanel.repaint();
            }
        });

        controlPanel.addListener(new SizeEventHandler() {
            @Override
            public void timeToChangeSize() {
                painter.getPlane().setXMin(controlPanel.getXMin());
                painter.getPlane().setXMax(controlPanel.getXMax());
                painter.getPlane().setYMin(controlPanel.getYMin());
                painter.getPlane().setYMax(controlPanel.getYMax());
                pointPainter.getPlane().setXMin(controlPanel.getXMin());
                pointPainter.getPlane().setXMax(controlPanel.getXMax());
                pointPainter.getPlane().setYMin(controlPanel.getYMin());
                pointPainter.getPlane().setYMax(controlPanel.getYMax());
                functionPainter.getPlane().setXMin(controlPanel.getXMin());
                functionPainter.getPlane().setXMax(controlPanel.getXMax());
                functionPainter.getPlane().setYMin(controlPanel.getYMin());
                functionPainter.getPlane().setYMax(controlPanel.getYMax());
                derivativePainter.getPlane().setXMin(controlPanel.getXMin());
                derivativePainter.getPlane().setXMax(controlPanel.getXMax());
                derivativePainter.getPlane().setYMin(controlPanel.getYMin());
                derivativePainter.getPlane().setYMax(controlPanel.getYMax());
                mainPanel.graphicsPanel.repaint();
            }
        });

        mainPanel.graphicsPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint.x = e.getX();
                startPoint.y = e.getY();
            }
        });

        mainPanel.graphicsPanel.addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                controlPanel.setXMin(controlPanel.getXMin() - e.getWheelRotation() / 10.);
                controlPanel.setXMax((controlPanel.getXMax() + e.getWheelRotation() / 10.));
                controlPanel.setYMin((controlPanel.getYMin() - e.getWheelRotation() / 10.));
                controlPanel.setYMax((controlPanel.getYMax() + e.getWheelRotation() / 10.));
            }
        });
        mainPanel.graphicsPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!pointPainter.isPointExist(e.getX(), e.getY()) && e.getButton() == MouseEvent.BUTTON1){
                    pointPainter.addPoint(e.getX(), e.getY());
                    functionPainter.addDot(e.getX(), e.getY());
                    mainPanel.rightPanel.addPoint(Converter.xScr2Crt(e.getX(), painter.getPlane()),
                            Converter.yScr2Crt(e.getY(), painter.getPlane()));
                    derivativePainter.setEmpty(false);
                }
                else if(pointPainter.isPointExist(e.getX(), e.getY()) && e.getButton() == MouseEvent.BUTTON3) {
                    pointPainter.removePoint(e.getX(), e.getY());
                    functionPainter.removeDot(e.getX(), e.getY());
                    mainPanel.rightPanel.removePoint(Converter.xScr2Crt(e.getX(), painter.getPlane()),
                            Converter.yScr2Crt(e.getY(), painter.getPlane()));
                    if(pointPainter.getPointsSize() == 0)
                        derivativePainter.setEmpty(true);
                    else
                        derivativePainter.setEmpty(false);
                }
                mainPanel.graphicsPanel.repaint();
            }
        });
        mainPanel.graphicsPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.getX() >= startPoint.x && e.getY() >= startPoint.y) {
                    var dx = Math.abs(Converter.xScr2Crt(e.getX(), painter.getPlane()) - Converter.xScr2Crt(startPoint.x, painter.getPlane()));
                    var dy = Math.abs(Converter.yScr2Crt(e.getY(), painter.getPlane()) - Converter.yScr2Crt(startPoint.y, painter.getPlane()));
                    controlPanel.setXMin(controlPanel.getXMin() - dx);
                    controlPanel.setXMax(controlPanel.getXMax() - dx);
                    controlPanel.setYMin(controlPanel.getYMin() + dy);
                    controlPanel.setYMax(controlPanel.getYMax() + dy);
                    startPoint.x = e.getX();
                    startPoint.y = e.getY();
                } else if (e.getX() <= startPoint.x && e.getY() >= startPoint.y) {
                    var dx = Math.abs(Converter.xScr2Crt(e.getX(), painter.getPlane()) - Converter.xScr2Crt(startPoint.x, painter.getPlane()));
                    var dy = Math.abs(Converter.yScr2Crt(e.getY(), painter.getPlane()) - Converter.yScr2Crt(startPoint.y, painter.getPlane()));
                    controlPanel.setXMin(controlPanel.getXMin() + dx);
                    controlPanel.setXMax(controlPanel.getXMax() + dx);
                    controlPanel.setYMin(controlPanel.getYMin() + dy);
                    controlPanel.setYMax(controlPanel.getYMax() + dy);
                    startPoint.x = e.getX();
                    startPoint.y = e.getY();
                } else if (e.getX() >= startPoint.x && e.getY() <= startPoint.y) {
                    var dx = Math.abs(Converter.xScr2Crt(e.getX(), painter.getPlane()) - Converter.xScr2Crt(startPoint.x, painter.getPlane()));
                    var dy = Math.abs(Converter.yScr2Crt(e.getY(), painter.getPlane()) - Converter.yScr2Crt(startPoint.y, painter.getPlane()));
                    controlPanel.setXMin(controlPanel.getXMin() - dx);
                    controlPanel.setXMax(controlPanel.getXMax() - dx);
                    controlPanel.setYMin(controlPanel.getYMin() - dy);
                    controlPanel.setYMax(controlPanel.getYMax() - dy);
                    startPoint.x = e.getX();
                    startPoint.y = e.getY();
                } else if (e.getX() <= startPoint.x && e.getY() <= startPoint.y) {
                    var dx = Math.abs(Converter.xScr2Crt(e.getX(), painter.getPlane()) - Converter.xScr2Crt(startPoint.x, painter.getPlane()));
                    var dy = Math.abs(Converter.yScr2Crt(e.getY(), painter.getPlane()) - Converter.yScr2Crt(startPoint.y, painter.getPlane()));
                    controlPanel.setXMin(controlPanel.getXMin() + dx);
                    controlPanel.setXMax(controlPanel.getXMax() + dx);
                    controlPanel.setYMin(controlPanel.getYMin() - dy);
                    controlPanel.setYMax(controlPanel.getYMax() - dy);
                    startPoint.x = e.getX();
                    startPoint.y = e.getY();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                pointPainter.resetPoint(e.getX(), e.getY());
                functionPanel.updateCoordinates(e.getX(), e.getY(), pointPainter.getPlane());
                mainPanel.graphicsPanel.repaint();
            }
        });
        functionPanel.addSmoothListener(new SmoothEventHandler() {
            @Override
            public void timeToChangeSmoothness(int x) {
                functionPainter.setParts(x);
                derivativePainter.setParts(x);
                mainPanel.graphicsPanel.repaint();
            }
        });
        functionPainter.addPolynomialListener(new PolynomialEventHandler() {
            @Override
            public void timeToChangePolynomial(String polynomial) {
                functionPanel.updatePolynomial(polynomial);
            }
        });
        upperPanel.addWithPointListener(new WithPointsEventHandler() {
            @Override
            public void timeToChangeState(boolean x) {
                pointPainter.isWithPoint(x);
                mainPanel.graphicsPanel.repaint();
            }
        });
        upperPanel.addWithGraphicsListener(new WithGraphicsEventHandler() {
            @Override
            public void timeToChangeState(boolean x) {
                functionPainter.isWithGraphics(x);
                mainPanel.graphicsPanel.repaint();
            }
        });
        upperPanel.addWithFullGraphListener(new WithFullGraphHandler() {
            @Override
            public void timeToChangeState(boolean x) {
                functionPainter.isWithFullGraphics(x);
                mainPanel.graphicsPanel.repaint();
            }
        });
        upperPanel.addNewPointListener(new AddNewPointHandler() {
            @Override
            public void timeToAddPoint(double x, double y) {
                if(!pointPainter.isPointExist(x, y)){
                    pointPainter.addPoint(x, y);
                    functionPainter.addDot(x, y);
                    mainPanel.rightPanel.addPoint(x, y);
                    derivativePainter.setEmpty(false);
                    mainPanel.graphicsPanel.repaint();
                }
            }
        });
        upperPanel.addDelPointListener(new DeletePointHandler() {
            @Override
            public void timeToDeletePoint(double x, double y) {
                if(pointPainter.isPointExist(x, y)) {
                    pointPainter.removePoint(x, y);
                    functionPainter.removeDot(x, y);
                    mainPanel.rightPanel.removePoint(x, y);
                    if(pointPainter.getPointsSize() == 0)
                        derivativePainter.setEmpty(true);
                    else
                        derivativePainter.setEmpty(false);
                    mainPanel.graphicsPanel.repaint();
                }
            }
        });
        mainPanel.rightPanel.addRemoveAllListener(new RemoveAllHandler() {
            @Override
            public void timeToRemoveAll() {
                pointPainter.removeAllPoints();
                functionPainter.removeAllDots();
                mainPanel.rightPanel.removeAllPoints();
                mainPanel.graphicsPanel.repaint();
                functionPanel.updatePolynomial("None");
                functionPanel.updateCoordinates(Converter.xCrt2Scr(0., painter.getPlane()),
                        Converter.yCrt2Scr(0, painter.getPlane()), painter.getPlane());
                controlPanel.setXMin(-5.1);
                controlPanel.setXMax(5.1);
                controlPanel.setYMin(-5.1);
                controlPanel.setYMax(5.1);
                derivativePainter.setEmpty(true);
            }
        });
        mainPanel.rightPanel.addSelectListener(new SelectEventHandler() {
            @Override
            public void timeToSelect(VPoint<Double> node) {
                pointPainter.resetPoint(node.getX(), node.getY());
                mainPanel.graphicsPanel.repaint();
            }
        });
        mainPanel.rightPanel.addDeleteNodeListener(new DeleteNodeHandler() {
            @Override
            public void timeToDeleteNode(VPoint<Double> node) {
                pointPainter.removePoint(node.getX(), node.getY());
                functionPainter.removeDot(node.getX(), node.getY());
                if(pointPainter.getPointsSize() == 0)
                    derivativePainter.setEmpty(true);
                else
                    derivativePainter.setEmpty(false);
                mainPanel.graphicsPanel.repaint();
            }
        });
        functionPainter.addPolynomialSetListener(new PolynomialSetHandler() {
            @Override
            public void timeToSetPolynomial(Newton n) {
                derivativePainter.setPolynomial(n);
                mainPanel.graphicsPanel.repaint();
            }
        });
        footer.addDerColorListener(new ColorDerListener() {
            @Override
            public void timeToChangeColor(Color color) {
                derivativePainter.setColor(color);
                mainPanel.graphicsPanel.repaint();
            }
        });
        footer.addFuncColorListener(new ColorFuncListener() {
            @Override
            public void timeToChangeColor(Color color) {
                functionPainter.setColor(color);
                mainPanel.graphicsPanel.repaint();
            }
        });
        derivativePainter.addPolynomialListener(new PolynomialHandler() {
            @Override
            public void timeToChangePolynomial(String polynomial) {
                footer.setPolynomial(polynomial);
            }
        });
    }
}

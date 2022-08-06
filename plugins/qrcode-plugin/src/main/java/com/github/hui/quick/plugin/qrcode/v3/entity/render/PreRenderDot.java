package com.github.hui.quick.plugin.qrcode.v3.entity.render;

import com.github.hui.quick.plugin.qrcode.v3.constants.RenderDotType;
import com.github.hui.quick.plugin.qrcode.v3.constants.RenderFunc;
import com.github.hui.quick.plugin.qrcode.v3.entity.svg.SvgTemplate;

import java.awt.*;

/**
 * 二维矩阵渲染
 *
 * @author YiHui
 * @date 2022/7/20
 */
public class PreRenderDot extends RenderDot {
    private int row, col;

    public PreRenderDot() {
        this.type = RenderDotType.PRE.getType();
    }

    public int getRow() {
        return row;
    }

    public PreRenderDot setRow(int row) {
        this.row = row;
        return this;
    }

    public int getCol() {
        return col;
    }

    public PreRenderDot setCol(int col) {
        this.col = col;
        return this;
    }

    private int w() {
        return col * size;
    }

    private int h() {
        return row * size;
    }

    @Override
    public void renderGeometry(Graphics2D g2d, RenderFunc.GeometryDrawFunc imgFunc) {
        imgFunc.draw(g2d, x, y, w(), h());
    }

    @Override
    public void renderImg(Graphics2D g2d, RenderFunc.ImgDrawFunc drawFunc) {
        drawFunc.draw(g2d, resource.getImg(), x, y, w(), h());
    }

    @Override
    public void renderTxt(Graphics2D g2d, RenderFunc.TxtImgDrawFunc drawFunc) {
        Font oldFont = g2d.getFont();
        if (oldFont.getSize() != size) g2d.setFont(resource.getFont(size));
        drawFunc.draw(g2d, resource.getText(), x, y, row * size);
        g2d.setFont(oldFont);
    }

    @Override
    public void renderSvg(SvgTemplate svg, RenderFunc.SvgDrawFunc drawFunc) {
        drawFunc.draw(svg, resource == null ? null : resource.getSvgId(), x, y, w(), h());
    }

    @Override
    public String toString() {
        return "PreRenderDot{" +
                "row=" + row +
                ", col=" + col +
                ", x=" + x +
                ", y=" + y +
                ", size=" + size +
                ", type=" + type +
                ", resource=" + resource +
                '}';
    }
}
/* generated @ 2018-11-05T13:38:34.254 */
import "./generated.attributes";

declare module "LayoutTypes" {
  enum AlignmentModeEnum_ { 'alignBounds', 'alignMargins' }

  interface GridLayoutAttributes extends ViewGroupAttributes {
    alignmentMode?: AlignmentModeEnum_;
    columnCount?: number;
    columnOrderPreserved?: boolean;
    orientation?: OrientationEnum__;
    rowCount?: number;
    rowOrderPreserved?: boolean;
    useDefaultMargins?: boolean;
  }

  interface GridLayoutLayoutParamsAttributes extends ViewGroupMarginLayoutParamsAttributes {
  }

  enum OrientationEnum__ { 'horizontal', 'vertical' }
}

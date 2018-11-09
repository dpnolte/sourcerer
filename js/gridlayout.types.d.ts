/* generated @ 2018-11-09T15:28:50.882 */
declare namespace GridlayoutTypes {
  enum AlignmentModeEnum_ { 'alignBounds', 'alignMargins' }

  interface GridLayoutAttributes extends MainTypes.ViewGroupAttributes {
    alignmentMode?: AlignmentModeEnum_;
    columnCount?: number;
    columnOrderPreserved?: boolean;
    orientation?: OrientationEnum__;
    rowCount?: number;
    rowOrderPreserved?: boolean;
    useDefaultMargins?: boolean;
  }

  interface GridLayoutLayoutParamsAttributes extends MainTypes.ViewGroupMarginLayoutParamsAttributes {
  }

  enum OrientationEnum__ { 'horizontal', 'vertical' }
}

/* generated @ 2018-11-09T18:39:27.391 */
/// <reference path='./main.types.d.ts' />

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

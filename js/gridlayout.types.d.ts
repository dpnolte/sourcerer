/* generated @ 2018-11-10T13:56:12.624 */
/// <reference path='./main.types.d.ts' />

declare namespace GridlayoutTypes {
  export enum AlignmentModeEnum_ { 'alignBounds', 'alignMargins' }

  export interface GridLayoutAttributes extends MainTypes.ViewGroupAttributes {
    alignmentMode?: AlignmentModeEnum_;
    columnCount?: number;
    columnOrderPreserved?: boolean;
    orientation?: OrientationEnum__;
    rowCount?: number;
    rowOrderPreserved?: boolean;
    useDefaultMargins?: boolean;
  }

  export interface GridLayoutLayoutParamsAttributes extends MainTypes.ViewGroupMarginLayoutParamsAttributes {
  }

  export enum OrientationEnum__ { 'horizontal', 'vertical' }
}

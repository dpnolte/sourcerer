import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from "./main";
// types
/* generated @ 2018-11-13T11:42:08.738 */
export namespace GridlayoutTypes {
  export enum AlignmentModeEnum {
    alignBounds = 'alignBounds',
    alignMargins = 'alignMargins'
  }
  export interface GridLayoutAttributes extends MainTypes.ViewGroupAttributes {
    alignmentMode?: AlignmentModeEnum;
    columnCount?: number;
    columnOrderPreserved?: boolean;
    orientation?: OrientationEnum;
    rowCount?: number;
    rowOrderPreserved?: boolean;
    useDefaultMargins?: boolean;
  }
  export interface GridLayoutLayoutParamsAttributes extends MainTypes.ViewGroupMarginLayoutParamsAttributes {
  }
  export enum OrientationEnum {
    horizontal = 'horizontal',
    vertical = 'vertical'
  }
}
// elements
export const GridlayoutGridLayout = (
  attributes?: GridlayoutTypes.GridLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<GridlayoutTypes.GridLayoutAttributes, LayoutParamAttributes> => {
  return element('gridlayout.gridLayout', attributes, children);
};
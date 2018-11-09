/* generated @ 2018-11-09T15:28:50.887 */
import { element } from "./element";
import { ElementNode } from "./element.types";
import { LayoutParamAttributes } from "./index.types";
export const GridlayoutGridLayout = (
  attributes?: GridlayoutTypes.GridLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<GridlayoutTypes.GridLayoutAttributes, LayoutParamAttributes> => {
  return element('gridlayout.gridLayout', attributes, children);
};
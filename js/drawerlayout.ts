/* generated @ 2018-11-09T15:28:32.259 */
import { element } from "./element";
import { ElementNode } from "./element.types";
import { LayoutParamAttributes } from "./index.types";
export const DrawerLayout = (
  attributes?: DrawerlayoutTypes.DrawerLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<DrawerlayoutTypes.DrawerLayoutAttributes, LayoutParamAttributes> => {
  return element('drawerLayout', attributes, children);
};
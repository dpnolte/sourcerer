/* generated @ 2018-11-09T15:28:39.303 */
import { element } from "./element";
import { ElementNode } from "./element.types";
import { LayoutParamAttributes } from "./index.types";
export const RecyclerView = (
  attributes?: RecyclerviewTypes.RecyclerViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<RecyclerviewTypes.RecyclerViewAttributes, LayoutParamAttributes> => {
  return element('recyclerView', attributes, children);
};
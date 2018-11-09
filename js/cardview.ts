/* generated @ 2018-11-09T15:28:33.358 */
import { element } from "./element";
import { ElementNode } from "./element.types";
import { LayoutParamAttributes } from "./index.types";
export const CardView = (
  attributes?: CardviewTypes.CardViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<CardviewTypes.CardViewAttributes, LayoutParamAttributes> => {
  return element('cardView', attributes, children);
};
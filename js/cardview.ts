/* generated @ 2018-11-09T18:39:06.019 */
import { element } from './element';
/// <reference path='./element.types.d.ts' />
/// <reference path='./layoutparams.types.d.ts' />
/// <reference path='./cardview.types.d.ts' />

export const CardView = (
  attributes?: CardviewTypes.CardViewAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<CardviewTypes.CardViewAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('cardView', attributes, children);
};
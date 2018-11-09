/* generated @ 2018-11-09T18:39:13.427 */
import { element } from './element';
/// <reference path='./element.types.d.ts' />
/// <reference path='./layoutparams.types.d.ts' />
/// <reference path='./recyclerview.types.d.ts' />

export const RecyclerView = (
  attributes?: RecyclerviewTypes.RecyclerViewAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<RecyclerviewTypes.RecyclerViewAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('recyclerView', attributes, children);
};
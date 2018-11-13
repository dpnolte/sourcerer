import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from "./main";
// types
/* generated @ 2018-11-13T11:38:20.232 */
export namespace RecyclerviewTypes {
  export interface GridLayoutManagerLayoutParamsAttributes extends RecyclerViewLayoutParamsAttributes {
  }
  export interface RecyclerViewAttributes extends MainTypes.ViewGroupAttributes {
  }
  export interface RecyclerViewLayoutParamsAttributes extends MainTypes.ViewGroupMarginLayoutParamsAttributes {
  }
  export interface StaggeredGridLayoutManagerLayoutParamsAttributes extends RecyclerViewLayoutParamsAttributes {
  }
}
// elements
export const RecyclerView = (
  attributes?: RecyclerviewTypes.RecyclerViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<RecyclerviewTypes.RecyclerViewAttributes, LayoutParamAttributes> => {
  return element('recyclerView', attributes, children);
};
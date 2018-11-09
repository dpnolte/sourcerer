/* generated @ 2018-11-09T15:28:55.764 */
import { element } from "./element";
import { ElementNode } from "./element.types";
import { LayoutParamAttributes } from "./index.types";
export const ColumnCardView = (
  attributes?: CarTypes.ColumnCardViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<CarTypes.ColumnCardViewAttributes, LayoutParamAttributes> => {
  return element('columnCardView', attributes, children);
};
export const ActionBar = (
  attributes?: CarTypes.ActionBarAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<CarTypes.ActionBarAttributes, LayoutParamAttributes> => {
  return element('actionBar', attributes, children);
};
export const PagedListView = (
  attributes?: CarTypes.PagedListViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<CarTypes.PagedListViewAttributes, LayoutParamAttributes> => {
  return element('pagedListView', attributes, children);
};
export const PagedScrollBarView = (
  attributes?: CarTypes.PagedScrollBarViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<CarTypes.PagedScrollBarViewAttributes, LayoutParamAttributes> => {
  return element('pagedScrollBarView', attributes, children);
};
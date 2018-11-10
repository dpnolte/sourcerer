/* generated @ 2018-11-10T13:56:18.147 */
import { element } from './element';
/// <reference path='./element.types.d.ts' />
/// <reference path='./layoutparams.types.d.ts' />
/// <reference path='./car.types.d.ts' />

export const ColumnCardView = (
  attributes?: CarTypes.ColumnCardViewAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<CarTypes.ColumnCardViewAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('columnCardView', attributes, children);
};
export const ActionBar = (
  attributes?: CarTypes.ActionBarAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<CarTypes.ActionBarAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('actionBar', attributes, children);
};
export const PagedListView = (
  attributes?: CarTypes.PagedListViewAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<CarTypes.PagedListViewAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('pagedListView', attributes, children);
};
export const PagedScrollBarView = (
  attributes?: CarTypes.PagedScrollBarViewAttributes & LayoutParamsTypes.LayoutParamAttributes,
  children?: Array<ElementTypes.ElementNode<unknown, LayoutParamsTypes.LayoutParamAttributes>>
): ElementTypes.ElementNode<CarTypes.PagedScrollBarViewAttributes, LayoutParamsTypes.LayoutParamAttributes> => {
  return element('pagedScrollBarView', attributes, children);
};

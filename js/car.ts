import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { MainTypes } from './main';
import { CardviewTypes } from './cardview';
// types
/* generated @ 2018-11-13T17:09:26.584 */
export namespace CarTypes {
  export interface ActionBarAttributes extends MainTypes.RelativeLayoutAttributes {
  }
  export interface ColumnCardViewAttributes extends CardviewTypes.CardViewAttributes {
    columnSpan?: number;
  }
  export enum DayNightStyleEnum {
    always_dark = 'always_dark',
    always_light = 'always_light',
    auto = 'auto',
    auto_inverse = 'auto_inverse'
  }
  export enum GutterEnum {
    both = 'both',
    end = 'end',
    none = 'none',
    start = 'start'
  }
  export interface PagedListViewAttributes extends MainTypes.FrameLayoutAttributes {
    dayNightStyle?: DayNightStyleEnum;
    downButtonIcon?: string;
    gutter?: GutterEnum;
    gutterSize?: string;
    scrollBarColor?: string;
    scrollBarContainerWidth?: string;
    scrollBarTopMargin?: string;
    upButtonIcon?: string;
  }
  export interface PagedScrollBarViewAttributes extends MainTypes.ViewGroupAttributes {
  }
}
// elements
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

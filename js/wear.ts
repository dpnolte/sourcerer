import { ElementNode, element } from './element';
import { LayoutParamAttributes } from './layoutparams';
import { RecyclerviewTypes } from "./recyclerview";
import { MainTypes } from "./main";
// types
/* generated @ 2018-11-12T13:41:43.450 */
export namespace WearTypes {
  export interface BoxInsetLayoutAttributes extends MainTypes.ViewGroupAttributes {
  }
  export interface BoxInsetLayoutLayoutParamsAttributes extends MainTypes.ViewGroupLayoutParamsAttributes {
    boxedEdges?: BoxedEdgesFlagsEnum[];
  }
  export enum BoxedEdgesFlagsEnum {
    all = 'all',
    bottom = 'bottom',
    left = 'left',
    none = 'none',
    right = 'right',
    top = 'top'
  }
  export interface CircularProgressLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }
  export interface SwipeDismissFrameLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }
  export interface WearableActionDrawerViewAttributes extends WearableDrawerViewAttributes {
    drawerTitle?: string;
  }
  export interface WearableDrawerLayoutAttributes extends MainTypes.FrameLayoutAttributes {
  }
  export interface WearableDrawerViewAttributes extends MainTypes.FrameLayoutAttributes {
    android_elevation?: string;
    enableAutoPeek?: boolean;
  }
  export interface WearableNavigationDrawerViewAttributes extends WearableDrawerViewAttributes {
  }
  export interface WearableRecyclerViewAttributes extends RecyclerviewTypes.RecyclerViewAttributes {
    bezelWidth?: number;
    circularScrollingGestureEnabled?: boolean;
    scrollDegreesPerScreen?: number;
  }
}
// elements
export const WearableNavigationDrawerView = (
  attributes?: WearTypes.WearableNavigationDrawerViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<WearTypes.WearableNavigationDrawerViewAttributes, LayoutParamAttributes> => {
  return element('wearableNavigationDrawerView', attributes, children);
};
export const BoxInsetLayout = (
  attributes?: WearTypes.BoxInsetLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<WearTypes.BoxInsetLayoutAttributes, LayoutParamAttributes> => {
  return element('boxInsetLayout', attributes, children);
};
export const WearableDrawerView = (
  attributes?: WearTypes.WearableDrawerViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<WearTypes.WearableDrawerViewAttributes, LayoutParamAttributes> => {
  return element('wearableDrawerView', attributes, children);
};
export const WearableActionDrawerView = (
  attributes?: WearTypes.WearableActionDrawerViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<WearTypes.WearableActionDrawerViewAttributes, LayoutParamAttributes> => {
  return element('wearableActionDrawerView', attributes, children);
};
export const WearableRecyclerView = (
  attributes?: WearTypes.WearableRecyclerViewAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<WearTypes.WearableRecyclerViewAttributes, LayoutParamAttributes> => {
  return element('wearableRecyclerView', attributes, children);
};
export const WearableDrawerLayout = (
  attributes?: WearTypes.WearableDrawerLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<WearTypes.WearableDrawerLayoutAttributes, LayoutParamAttributes> => {
  return element('wearableDrawerLayout', attributes, children);
};
export const SwipeDismissFrameLayout = (
  attributes?: WearTypes.SwipeDismissFrameLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<WearTypes.SwipeDismissFrameLayoutAttributes, LayoutParamAttributes> => {
  return element('swipeDismissFrameLayout', attributes, children);
};
export const CircularProgressLayout = (
  attributes?: WearTypes.CircularProgressLayoutAttributes & LayoutParamAttributes,
  children?: Array<ElementNode<unknown, LayoutParamAttributes>>
): ElementNode<WearTypes.CircularProgressLayoutAttributes, LayoutParamAttributes> => {
  return element('circularProgressLayout', attributes, children);
};